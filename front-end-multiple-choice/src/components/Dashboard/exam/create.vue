<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveExam()"
      @keydown="validate.$clearExternalResults()"
      class="w-3/6 rounded-md border-2 border-zinc-800 h-3/4 my-14 mb-32"
    >
      <h1 class="flex uppercase justify-center font-medium text-2xl my-10">
        Tạo đề thi cho <span class="text-red-600">{{ course.courseName }}</span>
      </h1>
      <div class="grid grid-cols-2 gap-2 md:gap-6 place-items-center mt-4">
        <div class="flex flex-col items-start">
          <label class="text-lg font-semibold">Tên khóa học: </label>
          <p>{{ course.courseName }}</p>
        </div>
        <div class="flex flex-col items-start">
          <label class="text-lg font-semibold">Tên môn học: </label>
          <p>{{ course.subject }}</p>
        </div>
        <div class="flex flex-col col-span-2 w-full ml-60">
          <label class="text-lg font-semibold">Tên giáo viên : </label>
          <p>{{ course.fullName }}</p>
        </div>
        <div>
          <p class="font-bold px-2">Số lượng câu hỏi</p>
          <input
            v-model="form.numQuestion"
            class="rounded-md h-8 w-64 pl-2 border-2"
            type="number"
            min="1"
            max="1000"
            required
            placeholder="Số lượng câu hỏi"
          />
        </div>
        <div>
          <p class="font-bold px-2">Thời lượng</p>
          <input
            v-model="form.duration"
            class="rounded-md h-8 w-64 pl-2 border-2"
            min="1"
            max="1000"
            required
            placeholder="Số lượng câu hỏi"
          />
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
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import { useRoute } from "vue-router";
import { required, alpha, minLength, maxLength } from "@vuelidate/validators";
import VueTimepicker from "vue3-timepicker";
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const rules = {
  duration: {
    required,
  },
  numQuestion: {
    required,
    minLength: minLength(1),
    maxLength: maxLength(50),
  },
};
const course = ref({});
const route = useRoute();
function padTo2Digits(num) {
  return num.toString().padStart(2, "0");
}
function toHoursAndMinutes(totalMinutes) {
  const minutes = totalMinutes % 60;
  const hours = Math.floor(totalMinutes / 60);

  return `${padTo2Digits(hours)}:${padTo2Digits(minutes)}:00`;
}
const form = ref({
  courses: {
    courseId: 0,
    courseName: course.value.courseName,
    imageCourse: "",
    numberStudent: 0,
    status: true,
    subject: course.value.subject,
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
  timeStart: "01:00:00",
  timeEnd: "02:00:00",
  duration: "",
  examId: 0,
  numQuestion: 0,
});
console.log(form.value.duration.toString());
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};

const validateFormCourse = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Hãy kiểm tra lại các trường thông tin.");
    return;
  }
};
const getCourseById = async () => {
  const { data } = await api.get(`/courses/${route.params.idcourse}`);
  course.value = data;
  form.value.courses = course.value;
};
const saveExam = async () => {
  try {
    form.value.duration = toHoursAndMinutes(form.value.duration);
    const data = await api.post(`/course/exam/add`, form.value);
    console.log(data);
  } catch (e) {
    console.error(e);
  }
};
onBeforeMount(() => {
  getCourseById();
});
</script>
