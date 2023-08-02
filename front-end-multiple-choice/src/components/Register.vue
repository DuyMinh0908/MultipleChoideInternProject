<template>
  <section class="items-center justify-center p-10 md:py-44">
    <form
      class="w-80 pb-10 md:pb-16 md:w-1/2 bg-white mx-auto rounded-lg h-full flex flex-col items-center"
    >
      <Icon name="logo" class="w-24 md:w-32 h-20 md:h-28" />
      <p class="font-bold text-sm md:text-xl mt-2 text-center">
        Đăng ký tài khoản
      </p>

      <div
        class="grid grid-cols-1 lg:grid-cols-2 gap-2 md:gap-6 place-items-center mt-6"
      >
        <div>
          <input
            class="border text-sm md:text-lg border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black"
            placeholder="Nhập họ tên"
          />
        </div>
        <div>
          <input
            placeholder="Nhập số diện thoại"
            class="placeholder-black border border-black text-sm md:text-lg rounded-md h-10 md:h-12 w-52 md:w-72 pl-2"
          />
        </div>
        <div>
          <input
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
            placeholder="Nhập email"
          />
        </div>
        <div>
          <input
            placeholder="Tên đăng nhập"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
          />
        </div>
        <div>
          <input
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
            placeholder="Mật khẩu"
          />
        </div>
        <div>
          <input
            placeholder="Xác nhận mật khẩu"
            class="border border-black rounded-md h-10 md:h-12 w-52 md:w-72 pl-2 placeholder-black text-sm md:text-lg"
          />
        </div>
      </div>
      <button
        class="rounded-md text-white bg-yellow-500 w-36 h-10 md:h-12 mt-7 md:mt-10 text-sm md:text-lg"
        @click.prevent="onLogin"
      >
        Đăng ký
      </button>
    </form>

    <!-- <div id="app"></div> -->
  </section>
</template>
<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
import { api } from "../services/http-common";
import { ref } from "vue";
const form = ref({
  username: null,
  userPass: null,
  fullName: null,
  email: null,
  phone: null,
  address: null,
  imageUser: null,
});
const onLogin = async () => {
  const { data } = await api.get(`users/getAll`);
  console.log(data);
};
</script>
