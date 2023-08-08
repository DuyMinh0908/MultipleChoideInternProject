<template>
  <Navigation />
  <div class="container">
    <table class="w-full text border-collapse border border-slate-400">
      <caption class="caption-top uppercase text-3xl font-bold">
        Danh sách bài viêt
      </caption>
      <thead class="bg-green-600">
        <tr class="uppercase">
          <th scope="col" class="border border-slate-300 col-span-1">ID</th>
          <th scope="col" class="border border-slate-300">Tiêu đề</th>
          <th scope="col" class="border border-slate-300">Nội dung</th>
          <th scope="col" class="border border-slate-300">Lượt xem</th>
          <th scope="col" class="border border-slate-300">Action</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="blog in allBlogs"
          :key="Number(blog.blogId)"
          class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 text-justify"
        >
          <td class="w-20 px-5">{{ blog.blogId }}</td>
          <td class="w-80">
            {{ blog.titleBlog }}
          </td>
          <td class="line-clamp-4 px-5">
            {{ blog.contentBlog }}
          </td>
          <td class="w-52 px-5">
            {{ blog.numberVisitors }}
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
                @click="deleteBlogItem(blog.blogId)"
                name="delete"
                class="w-6 h-6 fill-red-500 cursor-pointer"
              />
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <ModalsContainer />
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { Blog } from "../../../model/blog";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import Icon from "../../../icons/ClientDashboard.vue";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";

const allBlogs: Ref<Array<Blog>> = ref([]);
const currentIdBlog: Ref<Number | undefined> = ref(undefined);
const deleteConfirmationModal: Ref<boolean> = ref(false);

const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Xác nhận!",

    async onConfirm() {
      try {
        const data = await api.delete(
          `/blogs/user-blogs/delete/${currentIdBlog.value}`
        );
        await getAllBlogs();
      } catch (e) {
        console.error(e);
      }
      close();
    },
    onReject() {
      currentIdBlog.value = undefined;
      deleteConfirmationModal.value = false;
      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">Bạn có chắc chắn muốn xóa</p>`,
  },
});
const deleteBlogItem = (id: Number) => {
  currentIdBlog.value = id;
  open();
};
const getAllBlogs = async () => {
  try {
    const data = await api.get("/blogs");
    allBlogs.value = data.data.content;
    console.log(allBlogs.value);
  } catch (e) {
    console.error(e);
  }
};

onBeforeMount(() => {
  getAllBlogs();
});
</script>
