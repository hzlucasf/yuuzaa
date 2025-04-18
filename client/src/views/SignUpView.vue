<script lang="ts" setup>
import Button from '@/components/Button.vue'

import BackIcon from '@/assets/icons/back-icon.svg'

import { useRouter } from 'vue-router'

import View from '@/components/View.vue'

import SignUpForm from '@/components/SignUpForm.vue'

const router = useRouter()

const back = () => router.back()
</script>

<template>
    <Button :icon="BackIcon" :title="'Back'" @click="back" />

    <View>
        <SignUpForm />
    </View>
</template>

<style scoped>
button {
    position: fixed;
}
</style>
