<script lang="ts" setup>
defineProps<{ icon: string, message: string, type: 'success' | 'error' }>()
</script>

<template>
    <div class="notificiation" :style="type == 'success' ? 'background-color: #16a34a' : 'background-color: #dc2626'">
        <img :src="icon" />

        <p>{{ message }}</p>
    </div>
</template>

<style>
.notificiation {
    align-items: center;

    animation: fadeInOut 5s ease-in-out forwards;

    border-radius: 5px;

    bottom: 0px;

    display: flex;

    flex-direction: row;

    gap: 16px;

    justify-content: center;

    margin: 8px;

    padding: 16px 32px;

    position: absolute;

    right: 0px;
}

p {
    color: #fafafa;

    font-family: 'Noto Sans';

    font-weight: bolder;

    text-wrap: nowrap;
}
</style>
