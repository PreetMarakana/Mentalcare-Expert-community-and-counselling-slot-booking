package com.scheduling.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduling.dto.CommentAnswerRepliesRequestDto;
import com.scheduling.dto.CommentRepliesDto;
import com.scheduling.responses.ResponseEntity;
import com.scheduling.services.CommentAnswerRepliesService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentOnAnswerReplieController {

    @Autowired
    private CommentAnswerRepliesService commentAnswerRepliesService;

    @PostMapping(value = "/secure/commentAnswerReply/commentAnswers/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity commentReply(@PathVariable("commentId") Long commentId,
            @RequestBody CommentAnswerRepliesRequestDto answerRepliesRequestDto, BindingResult results,
            @AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(results, "comment", "can't be empty");

        if (results.hasErrors()) {
            responseEntity.setData(results.getAllErrors());
            responseEntity.setStatus("invalid data");
            responseEntity.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
            return responseEntity;
        }
        if (userDetails != null) {
            if (commentAnswerRepliesService.createComment(userDetails.getUsername(), commentId,
                    answerRepliesRequestDto)) {
                responseEntity.setData("done");
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setStatus("success");
                return responseEntity;
            }
        }
        responseEntity.setData("not done");
        responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseEntity.setStatus("not success");
        return responseEntity;
    }

    @DeleteMapping(value = "/secure/answerCommentReplys/{commentReplyId}")
    public ResponseEntity deleteCommentReply(@PathVariable("commentReplyId") Long commentReplyId,
            @AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity responseEntity = new ResponseEntity();
        if (commentReplyId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        if (userDetails != null) {
            if (commentAnswerRepliesService.deleteCommentReplies(commentReplyId, userDetails.getUsername())) {
                responseEntity.setData("done");
                responseEntity.setHttpStatus(HttpStatus.OK);
                responseEntity.setStatus("success");
                return responseEntity;
            }
        }
        responseEntity.setData("not done");
        responseEntity.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        responseEntity.setStatus("not success");
        return responseEntity;
    }

    @GetMapping(value = "/commentAnswers/{commentsId}")
    public ResponseEntity getCommentsReplies(@PathVariable("commentsId") Long commentsId,
            @AuthenticationPrincipal UserDetails userDetails) {

        ResponseEntity responseEntity = new ResponseEntity();
        if (commentsId == null) {
            responseEntity.setData("invalid date");
            responseEntity.setStatus("failed");
            responseEntity.setHttpStatus(HttpStatus.OK);
            return responseEntity;
        }
        List<CommentRepliesDto> commentResponseDtos = commentAnswerRepliesService.getcommentsreplies(commentsId,
                userDetails);
        responseEntity.setData(commentResponseDtos);
        responseEntity.setStatus("done");
        responseEntity.setHttpStatus(HttpStatus.OK);
        return responseEntity;
    }

}
