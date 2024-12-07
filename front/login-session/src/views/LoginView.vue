<template>
  <div class="container mt-5">
    <form class="p-4 border rounded shadow-sm bg-light" @submit.prevent="login">
      <fieldset>
        <legend class="text-center mb-4">Session Login</legend>

        <div class="mb-3">
          <label for="loginId" class="form-label">Login ID</label>
          <input
            type="text"
            id="loginId"
            class="form-control"
            v-model.trim="loginId"
            placeholder="Enter your login ID"
          />
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            v-model.trim="password"
            aria-describedby="PwdHelp"
            placeholder="Enter your password"
          />
          <small id="PwdHelp" class="form-text text-muted"
            >Password should be at least 8 characters long.</small
          >
        </div>

        <button type="submit" class="btn btn-primary w-100">Login</button>

        <button type="button" class="btn btn-primary w-100" @click="goToSignup">
          회원가입
        </button>
      </fieldset>
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useUserStore } from "../stores/user";
const userStore = useUserStore();
const router = useRouter();

const loginId = ref("");
const password = ref("");

// 로그인
const login = function () {
  userStore.login(loginId.value, password.value);
  loginId.value = "";
  password.value = "";
};

// 회원가입 페이지 이동
const goToSignup = function () {
  router.push({ name: "signup" });
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
}
fieldset {
  padding: 20px;
  border-radius: 5px;
}
legend {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
}
input::placeholder {
  color: #aaa;
}
button {
  font-size: 1rem;
  padding: 10px;
  margin: 10px 0;
}
</style>
