<template>
  <Navigation />
  <SideBarDasboard />
  <div class="container">
    <router-link :to="{ name: 'Dashboard.Course.Create' }">
      them khoa hoc
    </router-link>
    <table class="w-full text border-collapse border border-slate-400">
      <caption class="caption-top uppercase text-3xl font-bold">
        Danh sách khóa học
      </caption>
      <thead class="bg-green-600">
        <tr class="uppercase">
          <th scope="col" class="border border-slate-300 col-span-1">ID</th>
          <th scope="col" class="border border-slate-300">Tên khóa học</th>
          <th scope="col" class="border border-slate-300">Giáo viên</th>
          <th scope="col" class="border border-slate-300">Số học sinh</th>
          <th scope="col" class="border border-slate-300">Action</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="course in allCouses"
          :key="Number(course.courseId)"
          class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 text-justify"
        >
          <td class="w-20 px-5">{{ course.courseId }}</td>
          <td class="w-80">
            {{ course.courseName }}
          </td>
          <td class="line-clamp-4 px-5">
            {{ course.fullName }}
          </td>
          <td class="w-52 px-5">
            {{ course.numberStudent }}
          </td>
          <td class="w-60">
            <div class="flex flex-row space-x-4 w-full justify-center">
              <router-link
                :to="{
                  name: 'Dashboard.Blog.Update',
                  params: { id: Number(blog.blogId) },
                }"
              >
                <Icon
                  name="edit"
                  class="w-6 h-6 stroke-green-400 cursor-pointer"
                />
              </router-link>
              <Icon
                @click="deleteBlogItem(course.blogId)"
                name="delete"
                class="w-6 h-6 fill-red-500 cursor-pointer"
              />
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    <Pagination />
    <!-- <ModalsContainer /> -->
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { Course } from "../../../model/course";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import Icon from "../../../icons/ClientDashboard.vue";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import SideBarDasboard from "../SideBarDashboard.vue";
import Pagination from "../../Pagination.vue";
const allCouses: Ref<Array<Course>> = ref([]);
const currentIdCourse: Ref<Number | undefined> = ref(undefined);
const deleteConfirmationModal: Ref<boolean> = ref(false);

const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Xác nhận!",

    async onConfirm() {
      try {
        const data = await api.delete(
          `/blogs/user-blogs/delete/${currentIdCourse.value}`
        );
        await getAllBlogs();
      } catch (e) {
        console.error(e);
      }
      close();
    },
    onReject() {
      currentIdCourse.value = undefined;
      deleteConfirmationModal.value = false;
      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">Bạn có chắc chắn muốn xóa</p>`,
  },
});
const deleteBlogItem = (id: Number) => {
  currentIdCourse.value = id;
  open();
};
const getAllBlogs = async () => {
  try {
    const data = await api.get("/courses");
    allCouses.value = data.data.content;
    console.log(allCouses.value);
  } catch (e) {
    console.error(e);
  }
};

onBeforeMount(() => {
  getAllBlogs();
});
</script>
