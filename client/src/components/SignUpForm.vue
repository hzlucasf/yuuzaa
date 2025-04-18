<script lang="ts" setup>
import { ref } from 'vue'

import axios, { AxiosError } from 'axios'

import SubmitInput from './SubmitInput.vue'

import ErrorNotification from './ErrorNotification.vue'

import SuccessNotification from './SuccessNotification.vue'

const username = ref<string>('')

const password = ref<string>('')

const passwordConfirmation = ref<string>('')

const showErrorNotification = ref<boolean>(false)

const errorNotificationMessage = ref<string>('')

const showSuccessNotification = ref<boolean>(false)

const successNotificationMessage = ref<string>('')

const signUp = async () => {
    showSuccessNotification.value = false

    showErrorNotification.value = false

    try {
        if (password.value != passwordConfirmation.value) {
            throw Error('invalid password confirmation')
        }

        await axios.post('http://localhost:8080/sign-up', { username: username.value, password: password.value })

        username.value = ''

        password.value = ''

        passwordConfirmation.value = ''

        showSuccessNotification.value = true

        successNotificationMessage.value = 'user successfully registered'
    } catch (err) {
        showErrorNotification.value = true

        if (err instanceof AxiosError) {
            if (err.response) {
                errorNotificationMessage.value = err.response?.data.message ?? 'an unexpected error occurred'

                if (errorNotificationMessage.value == 'user already registered') {
                    username.value = ''
                }
            } else {
                errorNotificationMessage.value = 'unable to connect to the server. please check your connection or try again later'
            }
        } else {
            errorNotificationMessage.value = 'an unexpected error occurred'
        }

        password.value = ''

        passwordConfirmation.value = ''
    }
}
</script>

<template>
    <div class="component">
        <form class="sign-up-form" @submit.prevent="signUp">
            <input class="input username-input" type="text" placeholder="Username" required v-model="username" />

            <input class="input password-input" type="password" placeholder="Password" required v-model="password" />

            <input class="input password-confirmation-input" type="password" placeholder="Password Confirmation" required v-model="passwordConfirmation" />

            <SubmitInput :value="'Sign Up'" />
        </form>

        <p>Already have an account? <a href="/sign-in">Sign In</a></p>
    </div>

    <ErrorNotification v-if="showErrorNotification" :message="errorNotificationMessage" />

    <SuccessNotification v-if="showSuccessNotification" :message="successNotificationMessage" />
</template>

<style scoped>
.sign-up-form {
    display: flex;

    flex-direction: column;

    gap: 16px;
}
</style>
