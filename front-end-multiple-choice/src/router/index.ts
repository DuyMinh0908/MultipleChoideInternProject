import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: () => import("../components/HomePage.vue"),
  },
  {
    path: "/home",

    component: () => import("../components/HomePage.vue"),
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../components/Login.vue"),
  },
  {
    path: "/register",
    name: "Register",
    component: () => import("../components/Register.vue"),
  },
  {
    path: "/blog",
    name: "Blogs",
    meta: {
      title: "Blog",
    },
    children: [
      {
        path: "",
        name: "Blogs.Index",
        component: () => import("../components/Blog.vue"),
      },
      {
        path: ":id",
        name: "Blogs.Detail",
        component: () => import("../components/Blog/Detail.vue"),
      },
    ],
  },
  {
    path: "/exam",
    name: "Exam",
    component: () => import("../components/Exam.vue"),
  },
  {
    path: "/course",
    name: "Courses",
    meta: {
      title: "Courses",
    },
    children: [
      {
        path: "",
        name: "Courses.Index",
        component: () => import("../components/Coure.vue"),
        meta: {
          title: "Courses",
        },
      },
      {
        path: ":slug",
        name: "Courses.Detail",
        component: () => import("../components/Course/Detail.vue"),
        meta: {
          title: "Courses",
        },
      },
    ],
  },
  {
    path: "/regiter",
    component: () => import("../components/Register.vue"),
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    // always scroll to top
    return { top: 0 };
  },
});

export default router;
