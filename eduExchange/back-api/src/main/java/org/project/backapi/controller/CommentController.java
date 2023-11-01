    package org.project.backapi.controller;

    import jakarta.validation.Valid;
    import org.project.backapi.domain.Comment;
    import org.project.backapi.domain.User;
    import org.project.backapi.dto.CommentDto;
    import org.project.backapi.service.CommentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/comments")
    public class CommentController {
        private final CommentService commentService;

        @Autowired
        public CommentController(CommentService commentService) {
            this.commentService = commentService;
        }


        @GetMapping("/post/{postId}")
        public List<Comment> getAllCommentsByPostId(@PathVariable Long postId) {
            return commentService.getAllCommentsByPostId(postId);
        }

        //Get all comments of specific post
        @PostMapping("createComment/{postId}/{userId}")
        public ResponseEntity<CommentDto> createComment(
                                                        @PathVariable Long postId,
                                                        @PathVariable Long userId,
                                                        @Valid @RequestBody CommentDto commentDto) {

            CommentDto savedCommentDto = commentService.createComment(commentDto,postId,userId);
            return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
        }

        // Get comments of a comment
        @GetMapping("/{commentId}/replies")
        public ResponseEntity<List<Comment>> getRepliesToComment(@PathVariable Long commentId) {
            List<Comment> commentReplies = commentService.getRepliesToComment(commentId);
            return ResponseEntity.ok(commentReplies);
        }


    }