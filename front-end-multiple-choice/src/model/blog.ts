import { User } from "./user";
export interface Blog {
  blogId: Number;
  titleBlog: String;
  contentBlog: String;
  numberVisitors: String;
  user: User;
}
