import { defineStore } from "pinia";
import { User } from "../model/user";

type AuthState = {
  user: User;
  token?: string | null;
};

const defaultUser: User = {
  user_id: 0,
  address: "Unknown",
  email: "Unknown",
  full_name: "Unknpown",

  image_user: "Unknpown",
  phone: "Unknpown",
  user_name: "Unknpown",
};

export const authStore = defineStore("auth", {
  state: (): AuthState => ({
    user: defaultUser,
    token: null,
  }),
  getters:{
    isAuthorized: (state){
      if(localStorage.getItem("token")){
        state.token = localStorage.getItem("token")?.toString();

      }
      if(localStorage.getItem("currentUser")){
        state.user = JSON.parse(
          localStorage.getItem("currentUser")?.toString()
        );
      }
      return !!state.token;
    }
  }
});
