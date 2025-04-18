<template>
    <div class="view">
        <slot>
        </slot>
    </div>
</template>

<style scoped>
.view {
    align-items: center;

    background-color: #e5e5e5;

    box-sizing: border-box;

    display: flex;

    justify-content: center;

    min-height: 100vh;

    min-width: 100vw;

    padding: 64px;
}
</style>
