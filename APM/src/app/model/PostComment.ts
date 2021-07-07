import { Comment } from "./Comment";
import { Post } from "./post";

export class PostComment {
    post: Post;
    comments: Comment[];
}