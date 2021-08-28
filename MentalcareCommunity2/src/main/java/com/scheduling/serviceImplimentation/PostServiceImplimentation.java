package com.scheduling.serviceImplimentation;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.scheduling.dto.PostDto;
import com.scheduling.dto.PostResponseDto;
import com.scheduling.dto.UserResponseDto;
import com.scheduling.model.FileList;
import com.scheduling.model.Follow;
import com.scheduling.model.Post;
import com.scheduling.model.PostLike;
import com.scheduling.model.UserEntity;
import com.scheduling.repository.PostRepository;
import com.scheduling.services.PostService;
import com.scheduling.services.UserServices;

@Service
public class PostServiceImplimentation implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserServices userServices;
    
    @Override
    public PostResponseDto createPost(PostDto postDto, String username){
        UserEntity userEntity = userServices.getUserByEmail(username);
        
        try {
            if (userEntity!=null) {
                Post post = new Post();
                if (postDto.getCaption()==null) {
                    postDto.setCaption("");
                }
                post.setPostCaption(postDto.getCaption());
                post.setFileLists(getAllFileList(postDto.getFiles(),post));
                post.setPostDate(Date.valueOf(LocalDate.now()));
                post.setPostTime(Time.valueOf(LocalTime.now()));
                post.setUserEntity(userEntity);
                Post saved = postRepository.save(post);
                PostResponseDto postResponseDto = new PostResponseDto();
                postResponseDto.setPostId(saved.getPostId());
                postResponseDto.setCaption(saved.getPostCaption());
                postResponseDto.setFiles(getFileParths(saved.getFileLists()));
                postResponseDto.setPostDate(saved.getPostDate());
                postResponseDto.setPostTime(saved.getPostTime());
                postResponseDto.setUserResponseDto(new UserResponseDto(userEntity.getUserId(), userEntity.getUserName(), userEntity.getProfilePath()));
                return postResponseDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getFileParths(List<FileList> fileLists) {
        List<String> filePath = new ArrayList<>();
        fileLists.stream().forEach(file -> {
            filePath.add(file.getPath());
        });
        return filePath;
    }

    private List<FileList> getAllFileList(List<MultipartFile> files,Post post) {
        List<FileList> fileLists = new ArrayList<>();
        if (files!=null) {
            files.stream().forEach(file -> {
                try {
                    fileLists.add(new FileList(AwsService.uploadimage(file.getOriginalFilename(),file.getBytes()), post));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        
        return fileLists;
    }

    @Override
    public boolean deletePost(Long pid, String username) {
        try {
            UserEntity userEntity = userServices.getUserByEmail(username);
            Optional<Post> post = postRepository.findById(pid);
            if (post.isPresent()) {
                if (userEntity!=null) {
                    if (post.get().getUserEntity().equals(userEntity)) {
                        postRepository.delete(post.get());
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Post getPostById(Long postId) {
        try {
            Optional<Post> post = postRepository.findById(postId);   
            if (post.isPresent()) {
                return post.get();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<PostResponseDto> getPostForFeeds(UserEntity userEntity, Integer pageNo) {
        try {
            Set<PostResponseDto> postResponseDtos = new HashSet<>();
            Pageable pageable = PageRequest.of(pageNo-1,2,Sort.by("postTime"));
            if (userEntity!=null) {
                postRepository.getFeedPosts(pageable).getContent().stream().forEach(post -> {
                    PostResponseDto postResponseDto = new PostResponseDto();
                    if (!post.getPostCaption().equals("")) postResponseDto.setCaption(post.getPostCaption()); 
                    if (post.getFileLists()!=null) postResponseDto.setFiles(getFilePaths(post.getFileLists())); 
                     postResponseDto.setNoComments(Long.valueOf(post.getCommentOnPosts().size()));
                     postResponseDto.setNoLikes(Long.valueOf(post.getPostLikes().size()));
                     postResponseDto.setPostDate(post.getPostDate());
                     postResponseDto.setPostTime(post.getPostTime());
                     postResponseDto.setPostId(post.getPostId());
                     postResponseDto.setUserResponseDto(new UserResponseDto(post.getUserEntity().getUserId(), post.getUserEntity().getUserName(), post.getUserEntity().getProfilePath(),followOrNot(userEntity,post.getUserEntity())));
                     postResponseDto.setLiked(convertor(post.getPostLikes(),userEntity));
                     postResponseDtos.add(postResponseDto);
                });
            }else {
                postRepository.getFeedPosts(pageable).getContent().stream().forEach(post -> {
                    PostResponseDto postResponseDto = new PostResponseDto();
                    if (!post.getPostCaption().equals("")) postResponseDto.setCaption(post.getPostCaption()); 
                    if (post.getFileLists()!=null) postResponseDto.setFiles(getFilePaths(post.getFileLists())); 
                     postResponseDto.setNoComments(Long.valueOf(post.getCommentOnPosts().size()));
                     postResponseDto.setNoLikes(Long.valueOf(post.getPostLikes().size()));
                     postResponseDto.setPostDate(post.getPostDate());
                     postResponseDto.setPostTime(post.getPostTime());
                     postResponseDto.setPostId(post.getPostId());
                     postResponseDto.setUserResponseDto(new UserResponseDto(post.getUserEntity().getUserId(), post.getUserEntity().getUserName(), post.getUserEntity().getProfilePath(),followOrNot(null,post.getUserEntity())));
                     postResponseDtos.add(postResponseDto);
                });
            }
            return postResponseDtos;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new HashSet<>();
    }

    private boolean followOrNot(UserEntity loginUser, UserEntity postUser) {
        if(loginUser==null) return false;
        for (Follow follow : loginUser.getFollowing()) {
            if (follow.getToUser().equals(postUser)) {
                return true;
            }
        }
        return false;
    }

    private boolean convertor(List<PostLike> postLikes, UserEntity userEntity) {
        for (PostLike postLike : postLikes) {
            if (postLike.getUserEntity().equals(userEntity)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getFilePaths(List<FileList> fileLists) {
        List<String> list = new ArrayList<>();
        fileLists.stream().forEach(f1 -> {
            list.add(f1.getPath());
        });
        return list;
    }

    @Override
    public List<Post> getUserPosts(UserEntity profileUser, int pageNo) {
        try {
            Pageable pageable = PageRequest.of(pageNo-1, 2,Sort.by("postDate").and(Sort.by("postTime")));
            List<Post> posts = postRepository.getUserPosts(profileUser,pageable).getContent();
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
