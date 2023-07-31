<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
</script>

<template>
  <div class="bg-gray-50 p-20">
    <form class="bg-white flex flex-col items-center space-y-4 p-5">
      <Icon class="bg-contain w-20" name="logo" />
      <p>Đăng nhập tài khoản</p>
      <input placeholder="Tên đăng nhập" />
      <input placeholder="Mật khẩu" />
      <a class="text-sm md:tex-md">Quên mật khẩu?</a>
      <button class="bg-yellow-500 rounded-lg w-28 text-sm md:text-md">
        Đăng nhập
      </button>
      <p class="text-sm md:text-md space-x-2">
        Bạn chưa có tài khoản? <a class="text-red-500">Đăng ký !</a>
      </p>
    </form>
  </div>
</template>
