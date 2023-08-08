<script lang="ts" setup>
import { useRoute } from "vue-router";
import { Blog } from "../../../model/blog";
import Navigation from "../../Navigation.vue";
import SideBar from "../../SideBar.vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
const form = ref({
  blogId: 0,
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
const route = useRoute();
const getblogById = async () => {
  try {
    const { data } = await api.get(`/blogs/${route.params.id}`);
    form.value = data;
    console.log(form.value);
  } catch (e) {
    console.error(e);
  }
};
const updateBlog = async () => {
  try {
    const { data } = await api.put(
      `/blogs/user-blog/update/blogId=${form.value.blogId}`,
      form.value
    );
    console.log(data);
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getblogById();
});
</script>

<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col justify-center container">
    <form
      @submit.prevent="updateBlog"
      class="px-10 mt-20 py-10 border flex flex-col justify-center space-y-4 text-center rounded-xl"
    >
      <p class="uppercase text-2xl font-bold">Cập nhật {{}}</p>
      <div class="flex flex-col">
        <label>Tiêu đề khóa học</label>
        <input
          v-model="form.titleBlog"
          class="w-40 border border-black"
          type="text"
        />
      </div>
      <ckeditor v-model="form.contentBlog" :editor="ClassicEditor"></ckeditor>
      <button class="text-white bg-lightblue px-5 py-2 w-32 rounded-xl">
        Tao
      </button>
    </form>
  </div>
</template>
<style>
.ck-editor__editable_inline {
  min-height: 600px;
}
</style>
