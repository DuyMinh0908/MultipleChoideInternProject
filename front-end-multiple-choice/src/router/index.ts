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
    path: "/course",
    name: "Course",
    component: () => import("../components/Coure.vue"),
  },
  {
    path: "/exam",
    name: "Exam",
    component: () => import("../components/Exam.vue"),
  },
  {
    path: "/blog",
    name: "Blog",
    component: () => import("../components/Blog.vue"),
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
