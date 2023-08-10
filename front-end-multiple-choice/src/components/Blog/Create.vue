<script lang="ts" setup>
import Navigation from "../../components/Navigation.vue";
import SideBar from "../../components/SideBar.vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { api } from "../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useNotificationStore } from "../../store/notificationStore";
const notificationStore = useNotificationStore();
const router = useRouter();
const form = ref({
  titleBlog: "",
  contentBlog: "",
  numberVisitors: "",
  user: {
    userId: 1,
    username: "tom.denesik",
    userPass: "password",
    fullName: "Vandervort",
    email: "rayna.gulgowski@yahoo.com",
    phone: "0867987223",
    address: "Suite 810 5389 Andre Rapid, Hahnborough, WA 18463-3193",
    imageUser: "http://lorempixel.com/320/200/nature/",
    role: null,
  },
});
const createBlog = async () => {
  try {
    await api.post("/blogs/add", form.value);
    notificationStore.openSuccess("Thêm thành công");
    router.push("/blog");
  } catch (e) {
    console.error(e);
    notificationStore.openSuccess("Thêm không thành  công");
  }
};
</script>
<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col justify-center container">
    <form
      @submit.prevent="createBlog"
      class="px-10 mt-20 py-10 border flex flex-col justify-center space-y-4 text-center rounded-xl"
    >
      <p class="uppercase text-2xl font-bold">Tạo bài viết</p>
      <div class="flex flex-col items-start">
        <label
          >Tiêu đề khóa học <span class="text-red-600 font-bold">*</span></label
        >
        <input
          v-model="form.titleBlog"
          class="w-1/2 rounded-xl border border-black h-12"
          required
          type="text"
        />
      </div>
      <ckeditor v-model="form.contentBlog" :editor="ClassicEditor"></ckeditor>
      <button class="text-white bg-lightblue px-5 py-2 w-32 rounded-xl">
        Tạo
      </button>
    </form>
  </div>
</template>
<style>
.ck-editor__editable_inline {
  min-height: 600px;
}
</style>
