import { Role } from "./role";
export interface User {
  user_id: number;
  address: string;
  email: string;
  full_name: string;
  image_user: string;
  user_name: string;
  role: Array<Role>;
}
