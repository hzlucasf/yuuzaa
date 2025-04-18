<script lang="ts" setup>
import { ref } from 'vue'

import axios, { AxiosError } from 'axios'

import SubmitInput from './SubmitInput.vue'

import { useRouter } from 'vue-router'

import ErrorNotification from './ErrorNotification.vue'

const username = ref<string>('')

const password = ref<string>('')

const router = useRouter()

const redirectTo = (path: string) => router.push(path)

const showErrorNotification = ref<boolean>(false)

const errorNotificationMessage = ref<string>('')

const signIn = async () => {
    showErrorNotification.value = false

    try {
        const credentials = btoa(username.value + ':' + password.value)

        const response = await axios.post('http://localhost:8080/sign-in', {}, { headers: { Authorization: `Basic ${credentials}`} })

        localStorage.setItem('jwt', response.data.jwt)

        redirectTo('/')
    } catch (err) {
        showErrorNotification.value = true

        if (err instanceof AxiosError) {
            if (err.response) {
                errorNotificationMessage.value = err.response?.data.message ?? 'an unexpected error occurred'
            } else {
                errorNotificationMessage.value = 'unable to connect to the server. please check your connection or try again later'
            }
        } else {
            errorNotificationMessage.value = 'an unexpected error occurred'
        }

        username.value = ''

        password.value = ''
    }
}
</script>

<template>
    <div class="component">
        <form class="sign-in-form" @submit.prevent="signIn">
            <input class="input" type="text" placeholder="Username" required v-model="username" />

            <input class="input" type="password" placeholder="Password" required v-model="password" />

            <SubmitInput :value="'Sign In'" />
        </form>

        <p>Doesn't have an account? <a href="/sign-up">Sign Up</a></p>
    </div>

    <ErrorNotification v-if="showErrorNotification" :message="errorNotificationMessage" />
</template>

<style scoped>
.sign-in-form {
    display: flex;

    flex-direction: column;

    gap: 16px;
}
</style>
