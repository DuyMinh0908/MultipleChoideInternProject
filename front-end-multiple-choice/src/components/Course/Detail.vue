<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-row container pt-10 mx-auto space-x-20">
    <div class="flex flex-col space-y-4 w-2/3">
      <h3 class="font-bold text-3xl">{{ course.courseName }}</h3>
      <p>{{ course.subject }}</p>
      <h6 class="text-xl font-bold">Nội dung khóa học</h6>
      <div class="flex flex-row">
        <p class="font-bold mr-2">{{ course.chapters.length }}</p>
        Chương
      </div>
      <dropdown-menu v-for="(chapter, index) in course.chapters">
        <template #trigger>
          <div class="bg-gray-200 p-4 rounded-xl flex flex-row justify-between">
            <p
              class="text-xl font-bold flex flex-row items-center cursor-pointer"
            >
              <svg
                class="w-6 h-6 fill-red-600 stroke-red-600"
                viewBox="0 0 24 24"
                fill="red"
                xmlns="http://www.w3.org/2000/svg"
              >
                <path
                  d="M6 12H18M12 6V18"
                  stroke-width="2"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                />
              </svg>
              {{ index + 1 }} . {{ chapter.description }}
            </p>
            <span>{{ chapter.lessons.length }} bài học</span>
          </div>
        </template>
        <template #body>
          <ul
            class="px-10 py-2 text-gray-600"
            v-for="(lesson, index) in chapter.lessons"
          >
            <li>
              <span class="font-bold">{{ index + 1 }}</span> .
              {{ lesson.lessonTitle }}
            </li>
          </ul>
        </template>
      </dropdown-menu>
    </div>
    <div class="flex flex-col w-1/3 items-center space-y-4">
      <img :src="course.imageCourse" class="w-5/6 rounded-xl" />
      <h2 class="text-3xl text-lightblue">Miễn phí</h2>
      <template v-if="!isRegstered">
        <button
          @click="registerCourse()"
          class="uppercase rounded-xl bg-lightblue px-6 py-2 hover:bg-blue-500"
        >
          Đăng ký học
        </button>
      </template>
      <template v-else>
        <button
          @click="unSubCourse()"
          class="uppercase rounded-xl bg-lightblue px-6 py-2 hover:bg-blue-500"
        >
          Hủy đăng kí
        </button>
      </template>

      <div class="flex flex-col space-y-2">
        <span
          class="flex flex-row first-letter: justify-items-center text-center"
          ><Icon class="w-6 h-6 items-center mr-2" name="dashboard" /> Trình độ
          cơ bản</span
        >

        <span class="flex flex-row text-start"
          ><Icon class="w-5 h-5 items-center mr-2" name="film" /> Tổng số
          {{}}</span
        >

        <span class="flex flex-row space-x-2 text-start"
          ><Icon class="w-5 h-5 items-center mr-2" name="battery" /> Học mọi
          lúc, mọi nơi</span
        >
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../Navigation.vue";
import SideBar from "../SideBar.vue";
import { useRoute } from "vue-router";
import { Course } from "../../model/course";
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import { api } from "../../services/http-common";
import Icon from "../../icons/ClientDashboard.vue";
import { User } from "../../model/user";

const isRegstered: Ref<boolean> = ref(false);
const route = useRoute();
const courseRegistration: unknown = ref({
  course: {
    courseId: 0,
    courseName: "",
    imageCourse: "",
    numberStudent: 0,
    status: true,
    subject: "",
    teacher: {
      address: "",
      email: "",
      fullName: "",
      id: 2,
      imageTeacher: "",
      phone: "",
      role: {
        roleId: 0,
        roleName: "",
      },
      userPass: "",
      username: "",
    },
  },
  id: 0,
  user: {
    address: "",
    email: "",
    fullName: "",
    imageUser: "",
    hone: "",
    role: {
      roleId: 0,
      roleName: "",
    },
    userId: 4,
    userPass: "",
    username: "",
  },
});
const userInCourse: Ref<Array<User>> = ref([]);
const course: Ref<Course> = ref({
  courseId: 1,
  courseName: "",
  imageCourse: "",
  numberStudent: 0,
  status: true,
  subject: "",
});
const registerCourse = async () => {
  try {
    courseRegistration.value.course = course.value;
    const { data } = await api.post(
      `/course/subscribe`,
      courseRegistration.value
    );

    await getUserInCourse();
  } catch (e) {
    console.error(e);
  }
};
const unSubCourse = async () => {
  try {
    await api.delete(
      `course/un-subscribe/${courseRegistration.value.user.userId}&${course.value.courseId}`
    );
    isRegstered.value = false;
    await getUserInCourse();
  } catch (e) {
    console.error(e);
  }
};
const getDetailCourse = async () => {
  const { data } = await api.get(`/courses/${route.params.id}`);
  course.value = data;
};
const getUserInCourse = async () => {
  const { data } = await api.get(`/course/${route.params.id}/users`);
  userInCourse.value = data.userDto.content;
  userInCourse.value.forEach((e) => {
    if (e.userId === courseRegistration.value.user.userId) {
      isRegstered.value = true;
    }
  });
};
onMounted(() => {
  getDetailCourse();
  getUserInCourse();
});
onBeforeMount(async () => {
  await getDetailCourse();
  await getUserInCourse();
});
</script>
