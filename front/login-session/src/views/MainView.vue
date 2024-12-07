<template>
  <main>
    <header>
      <div v-if="userStore.loginUser.loginId">
        <h2>{{ userStore.loginUser.loginId }}</h2>
        <button @click="logout">로그아웃</button>
        <button @click="signout">회원탈퇴</button>
      </div>
      <div class="wrapper">
        <nav>
          <RouterLink to="/info">Info</RouterLink> |
          <RouterLink to="/board">Board</RouterLink>
        </nav>
      </div>
    </header>
    <RouterView />
  </main>
</template>

<script setup>
import { useUserStore } from "../stores/user";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const router = useRouter();

const logout = function () {
  userStore.logout();
};

const signout = function () {
  const password = prompt("회원탈퇴하려면 비밀번호를 입력하세요 : ");
  userStore.signout(password);
  router.push({ name: "login" });
};
</script>
<style scoped></style>
