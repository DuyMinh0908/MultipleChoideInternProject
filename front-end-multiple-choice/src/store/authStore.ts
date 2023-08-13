import { defineStore } from "pinia";

import { api } from "../services/http-common";

type AuthState = {
  id: Number;
  address: String;
  email: String;
  fullName: String;
  image: String;
  phone: String;
  username: String;
  password: String;
  role: Number;
};

// @ts-ignore
export const useAuthStore = defineStore("auth", {
  state: (): AuthState => ({
    id: 0,
    address: "",
    email: "",
    fullName: "",
    image: "",
    phone: "",
    username: "",
    password: "password",
    role: 3,
  }),
  getters: {
    isAuthorized: (state) => {
      if (localStorage.getItem("token")) {
        state. = localStorage.getItem("token")?.toString();
      }
      if (localStorage.getItem("currentUser")) {
        state.user = JSON.parse(
          // @ts-ignore
          localStorage.getItem("currentUser")?.toString()
        );
      }
      return !!state.token;
    },
    userType: (state) => {
      if (state.user && state.user.type) {
        return state.user.type;
      }
      return UserType.CLIENT;
    },
    roles() {
      // @ts-ignore
      if (!this.isAuthorized) return [];
      if (localStorage.getItem("roles")) {
        // @ts-ignore
        return JSON.parse(localStorage.getItem("roles"));
      }
      return [];
    },

    isAdmin() {
      // @ts-ignore
      if (!this.isAuthorized) return false;
      // @ts-ignore
      return !!this.roles.find((r: Role) => r.name === "Admin");
    },
    isReader() {
      // @ts-ignore
      if (!this.isAuthorized) return false;
      // @ts-ignore
      return !!this.roles.find((r: Role) => r.name === "Reader");
    },
    isUser() {
      if (!this.isAuthorized || this.isAdmin || this.isReader) return false;
      // @ts-ignore
      return true;
    },
    userId: (state) => {
      return state.id ? state.id : 0;
    },
  },
  actions: {
    can(permission: string) {
      // @ts-ignore
      if (this.isSuperAdmin) return true;
      return !!this.permissions?.find((p: Permission) => p.name === permission);
    },
    hasRole(roleName: string) {
      // @ts-ignore
      return !!this.roles.find((r: Role) => r.name === roleName);
    },
    setToken(token: string) {
      this.token = token;
      localStorage.setItem("token", token);
    },
    setCurrentUserStatus(status: RealtimeStatus) {
      this.user.realtime_status = status;
    },
    setCurrentUser(currentUser: User) {
      this.user = currentUser;
      if (currentUser) {
        localStorage.setItem("currentUser", JSON.stringify(currentUser));
      }
    },
    setRoles(roles: Array<Role>) {
      localStorage.setItem("roles", JSON.stringify(roles));
    },
   
    async refreshProfile() {
      try {
        const { data } = await api.get(`/auth/profile`);
        this.setCurrentUser(data.data.user);
        if (data.data.banned_readers) {
          const bannedReaders: Array<User> = [];
          data.data.banned_readers.forEach((bannedReader: any) => {
            bannedReaders.push(bannedReader.user);
          });
          this.setBannedReaders(bannedReaders);
        }
      } catch (error) {}
    },
    setBannedReaders(users: Array<User>) {
      this.bannedUsers = users;
    },
    async logOut() {
      try {
        if (this.token) {
          await api.post(`/change-realtime-status`, {
            user_id: this.userId,
            realtime_status: RealtimeStatus.OFFLINE,
          });
        }
      } catch (error) {}

   
      this.token = undefined;
      this.user = defaultUser;
      localStorage.removeItem("token");
      localStorage.removeItem("currentUser");
      localStorage.removeItem("roles");
      localStorage.removeItem("permissions");
    },
 
  },
});
