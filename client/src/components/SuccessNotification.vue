<script lang="ts" setup>
defineProps<{ message: string }>()

import Notification from './Notification.vue'

import SuccessIcon from '@/assets/icons/success-icon.svg'
</script>

<template>
    <Notification :icon="SuccessIcon" :message="message" :type="'success'" />
</template>
