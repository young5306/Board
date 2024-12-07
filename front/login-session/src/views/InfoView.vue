<template>
  <div class="about">
    <h2>This is info page</h2>
    <table class="table table-bordered">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">LoginId</th>
          <th scope="col">email</th>
          <!-- <th scope="col">pwd</th> -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in userStore.users">
          <th scope="row">{{ user.userId }}</th>
          <td>{{ user.loginId }}</td>
          <td>{{ user.email }}</td>
          <!-- <td>pwd</td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted } from "vue";
const userStore = useUserStore();
onMounted(() => {
  userStore.getUsers();
});
</script>
<style scoped></style>
