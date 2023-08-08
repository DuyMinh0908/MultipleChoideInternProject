<template>
  <section class="flex flex-row items-center justify-center p-20 md:p-44">
    <div class="bg-white w-72 md:w-1/3 p-10 rounded-lg">
      <form class="border-2 w-full flex flex-col items-center rounded-md">
        <Icon name="logo" class="w-24 md:w-28 h-24 md:h-28" />
        <p class="font-bold text-xs md:text-xl mt-3">Đăng nhập tài khoản</p>
        <div class="flex flex-col gap-4 mt-4">
          <div>
            <input
              v-model="form.username"
              class="w-48 md:w-72 border border-black rounded-md h-8 md:h-10 pl-2 placeholder-black text-xs md:text-lg"
              placeholder="Tên đăng nhập"
            />
          </div>
          <div>
            <input
              v-model="form.password"
              placeholder="Mật khẩu"
              class="placeholder-black border border-black rounded-md h-8 md:h-10 w-48 md:w-72 pl-2 text-xs md:text-lg"
            />
          </div>
        </div>
        <div
          class="font-semibold mt-2 md:mt-4 mr-24 md:mr-36 text-xs md:text-lg"
        >
          <button type="submit">Quên mật khẩu?</button>
        </div>
        <button
          class="rounded-md text-white bg-yellow-500 w-36 h-8 md:h-10 mt-5 text-xs md:text-lg"
          type="submit"
        >
          Đăng nhập
        </button>
        <div class="flex flex-row mt-5 mb-7 font-normal text-xs md:text-lg">
          <p>
            Bạn chưa có tài khoản?
            <router-link :to="{ name: 'Register' }"> Đăng ký !</router-link>
          </p>
        </div>
      </form>
    </div>
  </section>
</template>
<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { api } from "../services/http-common";
import { ref } from "vue";
const form = ref({
  username: null,
  password: null,
});
const onLogin = async () => {
  const { data } = await api.get(`users/getAll`);
  console.log(data);
};
</script>
