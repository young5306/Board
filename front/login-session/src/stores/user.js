import { ref } from "vue";
import axios from "axios";
import { defineStore } from "pinia";
import router from "@/router";

const REST_API_URL = `http://localhost:8080/user-api`;

export const useUserStore = defineStore(
  "user",
  () => {
    const loginUser = ref({ loginId: "" });
    const users = ref([]);

    // 로그인
    const login = function (loginId, password) {
      axios
        .post(
          `${REST_API_URL}/login`,
          { loginId, password },
          {
            withCredentials: true,
            // headers: { "Content-Type": "application/json" },
          }
        )
        .then((res) => {
          if (res.status === 200) {
            console.log(res);
            loginUser.value.loginId = res.data.loginId;
            console.log(loginUser.value.loginId);
            router.push({ name: "main" });
          }
        })
        .catch((err) => {
          alert(err);
        });
    };

    // 로그인 사용자 loginId 확인
    // const getLoginId = function () {
    //   return axios
    //     .get(`${REST_API_URL}/current`, {
    //       withCredentials: true,
    //     })
    //     .then((res) => {
    //       if (res.status === 200) {
    //         loginId.value = res.data.loginId;
    //       }
    //     })
    //     .catch((err) => {
    //       // console.log(err);
    //       alert(err.response.data);
    //     });
    // };

    // 회원가입
    const signup = function (data) {
      axios
        .post(`${REST_API_URL}/signup`, data)
        .then((res) => {
          alert(res.data);
        })
        .catch((err) => {
          alert(err.response.data);
        });
    };

    // 로그아웃
    const logout = function () {
      console.log("로그아웃 요청 보내기 전");
      axios
        .post(
          `${REST_API_URL}/logout`,
          {}, // 빈 데이터로 요청
          {
            withCredentials: true, // 헤더에 쿠키 포함
          }
        )
        .then((res) => {
          console.log("로그아웃 응답 : " + res);
          loginUser.value.loginId = null;
          localStorage.clear();
          alert(res.data);
          router.push({ name: "login" });
        })
        .catch((err) => {
          console.log("로그아웃 err : " + err);
          alert(err);
        });
    };

    // 회원탈퇴
    const signout = function (password) {
      axios
        .delete(`${REST_API_URL}/signout`, {
          data: { password },
          withCredentials: true,
        })
        .then((res) => {
          loginUser.value.loginId = null;
          localStorage.clear();
          alert(res.data);
          router.push({ name: "login" });
        })
        .catch((err) => {
          alert(err.response.data);
        });
    };

    // 사용자 목록
    const getUsers = function () {
      axios
        .get(REST_API_URL, {
          withCredentials: true,
        })
        .then((res) => {
          if (res.status === 200) {
            console.log(res);
            users.value = res.data;
          }
        })
        .catch((err) => {
          console.log(err);
          alert("에러 : " + err.response.data);
        });
    };

    return { loginUser, login, signup, logout, signout, getUsers, users };
  },
  {
    persist: true,
  }
);
