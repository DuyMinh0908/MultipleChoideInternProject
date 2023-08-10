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
    path: "/dashboard",
    name: "Dashboard",
    children: [
      {
        path: "",
        name: "Dashboard.Index",
        component: () => import("../components/Dashboard/dashboard.vue"),
      },
      {
        path: "/blog/index",
        name: "Dashboard.Blog.Index",
        component: () => import("../components/Dashboard/blog/index.vue"),
      },
      {
        path: ":id/update",
        name: "Dashboard.Blog.Update",
        component: () => import("../components/Dashboard/blog/update.vue"),
      },
      {
        path: "/course/index",
        name: "Dashboard.Course.Index",
        component: () => import("../components/Dashboard/course/index.vue"),
      },
      {
        path: "/course/create",
        name: "Dashboard.Course.Create",
        component: () => import("../components/Dashboard/course/create.vue"),
      },
      {
        path: ":id/update",
        name: "Dashboard.Course.Update",
        component: () => import("../components/Dashboard/course/update.vue"),
      },
      {
        path: "question/create",
        name: "Dashboard.Question.Create",
        component: () => import("../components/Dashboard/question/create.vue"),
      },
    ],
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
        path: ":id/detail",
        name: "Blogs.Detail",
        component: () => import("../components/Blog/Detail.vue"),
      },
      {
        path: "/create",
        name: "Blogs.Create",
        component: () => import("../components/Blog/Create.vue"),
      },
      {
        path: ":id/update",
        name: "Blogs.Update",
        component: () => import("../components/Blog/Update.vue"),
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
        path: ":id",
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
