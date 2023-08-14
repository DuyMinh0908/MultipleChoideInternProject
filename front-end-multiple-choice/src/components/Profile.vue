<template>
  <div>
    <Navigation />
    <SideBar />
    <div class="mt-3 mx-auto flex flex-col justify-center items-center w-3/4 h-full">
      <div>
        <img
          :src="'http://localhost:8080/api/v1/file/upload/' + authStore.image"
          class="w-36 h-36 rounded-full"
          alt=""
        />
        <input
          type="file"
          @change="onFileSelect"
          accept="image/png, image/jpeg, image/jpg"
        />
      </div>
      <!-- ... Other form fields ... -->
      <div class="mt-3 mx-auto flex flex-row justify-center w-3/4 h-full">
        <button
          class="rounded-xl text-white bg-lightblue w-36 h-10 md:h-12 mt-7 md:mt-10 text-sm md:text-lg"
          @click.prevent="updateUser()"
        >
          Save
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import Navigation from "../components/Navigation.vue";
import SideBar from "../components/SideBar.vue";
import { ref } from "vue";
import { api } from "../services/http-common";
import { useAuthStore } from "../store/authStore";
import { useNotificationStore } from "../store/notificationStore";
import {
  required,
  // ... Other validators ...
  sameAs,
} from "@vuelidate/validators";
import { useRouter } from "vue-router";

const authStore = useAuthStore();
const notificationStore = useNotificationStore();
const currentFile = ref();
const router = useRouter();

const form = ref({
  username: "",
  userPass: "",
  fullName: "",
  email: "",
  phone: "",
  address: "",
  imageUser: "",
  roleId: 3,
  comfirmPass: "",
});

const onFileSelect = (e: any): void => {
  const file = e.target.files[0];
  currentFile.value = file;
};

const updateUser = async () => {
  try {
    const formData = new FormData();
    formData.append("username", form.value.username);
    formData.append("userPass", form.value.userPass);
    formData.append("fullName", form.value.fullName);
    formData.append("email", form.value.email);
    formData.append("phone", form.value.phone);
    formData.append("address", form.value.address);
    if (currentFile.value) {
      formData.append("imageUser", currentFile.value);
    }

    const response = await api.put(`/user-connect/user/update/${authStore.id}`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });

    if (response.status === 200) {
      notificationStore.openSuccess("Update User Success.");
      await router.push("/");
    } else {
      notificationStore.openError("Error updating user.");
    }
  } catch (error) {
    notificationStore.openError("An error occurred while updating user.");
  }
};
</script>
