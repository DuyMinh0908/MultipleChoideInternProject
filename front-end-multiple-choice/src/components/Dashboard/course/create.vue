<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveCourse"
      @keydown="validate.$clearExternalResults()"
      class="w-3/6 rounded-md border-2 border-zinc-800 h-3/4 my-14 mb-32"
    >
      <h1 class="flex uppercase justify-center font-medium text-2xl my-10">
        Thêm khóa học
      </h1>
      <div class="grid grid-cols-2 gap-2 md:gap-6 place-items-center mt-4">
        <div>
          <p class="font-bold text-zinc-600 px-2">
            Tên khóa học<span class="text-red-600 font-bold">*</span>
          </p>
          <input
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            v-model="form.courseName.trim"
            id=""
            type="text"
            placeholder="Nhập mã khóa học"
          />
          <template v-if="validate.courseName.$error">
            <div
              v-for="(error, index) in validate.courseName.$errors"
              :key="index"
              class="text-danger mt-2 italic text-sm"
            >
              {{ validationMessage(error.$message, "Tên khóa học") }}
            </div>
          </template>
        </div>

        <div>
          <p class="font-bold text-zinc-600 px-2">
            Tên môn học <span class="text-red-600 font-bold">*</span>
          </p>
          <input
            v-model="form.subject.trim"
            class="rounded-md h-8 w-64 pl-2 border-2"
            name=""
            id=""
            type="text"
            required
            placeholder="Java"
          />
        </div>
        <div>
          <p class="font-bold text-zinc-600 px-2">Số lượng học sinh</p>
          <input
            v-model="form.numberStudent"
            class="rounded-md h-8 w-64 pl-2 border-2"
            type="number"
            min="1"
            max="1000"
            required
            placeholder="Số lượng học sinh"
          />
        </div>

        <div class="flex flex-row items-center justify-items-center space-x-2">
          <p class="font-bold text-zinc-600 px-2">
            Trạng thái <span class="text-red-600 font-bold">*</span>
          </p>
          <div class="flex items-center">
            <input
              id="default-radio-1"
              type="radio"
              value="true"
              v-model="form.status"
              name="default-radio"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <label
              for="default-radio-1"
              class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >Hoạt động
            </label>
          </div>
          <div class="flex items-center">
            <input
              checked
              id="default-radio-2"
              type="radio"
              value="false"
              name="default-radio"
              v-model="form.status"
              class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            />
            <label
              for="default-radio-2"
              class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
              >Đang đóng</label
            >
          </div>
        </div>
        <div>
          <p class="font-bold text-zinc-600 px-2">Image</p>
          <div
            class="h-56 w-60 rounded-md border-2 bg-white flex justify-center items-center"
          >
            <div class="w-14 h-fit py-2 bg-zinc-400 flex flex-col items-center">
              <label for="file-input">
                <img
                  class="rounded-full w-5 h-5"
                  src="src/assets/img/iccong.svg"
                  alt=""
                />
              </label>
              <input
                class="hidden"
                id="file-input"
                type="file"
                @change="onFileSelect"
              />
              <p class="font-bold text-xs">Import</p>
            </div>
          </div>
        </div>
      </div>
      <div class="flex-row gap-10 my-10 flex justify-center h-10">
        <button
          type="submit"
          class="rounded-md bg-green-300 w-20 hover:bg-green-600 font-bold text-white"
        >
          LƯU
        </button>
        <button
          class="rounded-md bg-zinc-400 w-20 hover:bg-red-600 font-bold text-white"
        >
          HỦY
        </button>
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, Ref } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import { useRouter } from "vue-router";
import { required, alpha, minLength, maxLength } from "@vuelidate/validators";
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const rules = {
  courseName: { required, minLength: minLength(3) },
  subject: { required, minLength: minLength(3) },
};

const router = useRouter();
const form = ref({
  courseName: "",
  teacherId: "",
  numberStudent: "",
  subject: "",
  status: "",
});
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
const currentFile = ref();

const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
};
const saveCourse = async () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Please check your validation fields.");
    return;
  }
  try {
    const formData = new FormData();
    formData.append("courseName", form.value.courseName);
    formData.append("teacherId", "2");
    formData.append("numberStudent", form.value.numberStudent);
    formData.append("subject", form.value.subject);
    formData.append("status", form.value.status),
      formData.append("imageCourse", currentFile.value, currentFile.value.name);
    await api.post("/courses/create-course", formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
    notificationStore.openSuccess("Tạo khóa học thành công.");
    await router.push("/course/index");
  } catch (e) {
    notificationStore.openError("Đã xảy ra lỗi");
  }
};
</script>
