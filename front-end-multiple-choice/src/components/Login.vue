<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { api } from "../services/http-common";
import { ref } from "vue";
import { useAuthStore } from "../store/authStore";
import { useNotificationStore } from "../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import { required, minLength, maxLength } from "@vuelidate/validators";
import { useRouter } from "vue-router";
const authStore = useAuthStore();
const notificationStore = useNotificationStore();
const $externalResults = ref({});
const form = ref({
  username: null,
  password: null,
});
const rules = {
  username: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
  password: {
    required,
    minLength: minLength(6),
    maxLength: maxLength(50),
  },
};
const router = useRouter();
const onLogin = async () => {
  try {
    const data = await api.post(
      "/user-connect/login",
      {},
      { params: form.value }
    );
    console.log(data.data.data);
    authStore.setId(data.data.data.id);
    authStore.setAddress(data.data.data.address);
    authStore.setFullName(data.data.data.fullName);
    authStore.setImage(data.data.data.image);
    authStore.setPhone(data.data.data.phone);
    authStore.setUserName(data.data.data.username);
    authStore.setRole(data.data.data.role);
    notificationStore.openSuccess(data.data.data.message);
    router.push("/home");
  } catch (e) {
    console.log(e);
  }
};
const validate = useVuelidate(rules, form, { $externalResults });
const validateFormLogin = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Hãy kiểm tra lại các trưỞng thông tin.");
    return;
  }
};
const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
</script>

<template>
  <section class="flex flex-row items-center justify-center p-20 md:p-44">
    <div class="bg-white w-72 md:w-1/3 p-10 rounded-lg">
      <form
        class="border-2 w-full flex flex-col items-center rounded-md"
        @submit.prevent="onLogin()"
        @keydown="validateFormLogin"
      >
        <Icon name="logo" class="w-24 md:w-28 h-24 md:h-28" />
        <p class="font-bold text-xs md:text-xl mt-3">Login</p>
        <div class="flex flex-col gap-4 mt-4">
          <div>
            <input
              v-model="form.username"
              class="w-48 md:w-72 border border-black rounded-md h-8 md:h-10 pl-2 placeholder-black text-xs md:text-lg"
              placeholder="Enter username"
            />
            <template v-if="validate.username.$error">
              <div
                v-for="(error, index) in validate.username.$errors"
                :key="index"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error.$message, "username") }}
              </div>
            </template>
          </div>
          <div>
            <input
              v-model="form.password"
              placeholder="Enter password"
              type="password"
              class="placeholder-black border border-black rounded-md h-8 md:h-10 w-48 md:w-72 pl-2 text-xs md:text-lg"
            />
            <template v-if="validate.password.$error">
              <div
                v-for="(error, index) in validate.password.$errors"
                :key="index"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error.$message, "password") }}
              </div>
            </template>
          </div>
        </div>

        <button
          class="text-white bg-lightblue rounded-xl w-36 h-8 md:h-10 mt-5 text-xs md:text-lg"
          type="submit"
        >
          Login
        </button>
        <div class="flex flex-row mt-5 mb-7 font-normal text-xs md:text-lg">
          <p>
            You are not have account?
            <router-link :to="{ name: 'Register' }"> Register !</router-link>
          </p>
        </div>
      </form>
    </div>
  </section>
</template>
