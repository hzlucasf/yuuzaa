<script lang="ts" setup>
defineProps<{ content: string }>()
</script>

<template>
    <h1 class="title">{{ content }}</h1>
</template>

<style scoped>
.title {
    color: #d4d4d4;

    font-family: 'Noto Sans JP';

    text-wrap: nowrap;
}

@media (max-width: 899px) {
    .title {
        font-size: 8rem;
    }
}

@media (min-width: 900px) {
    .title {
        font-size: 10rem;
    }
}
</style>
