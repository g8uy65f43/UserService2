<template>
    <div class="app">
        <h1>hello</h1>

        <h1>{{ name }}</h1>
        <h1>{{ age }}</h1>
        <button @click="showTel">tel</button>
    </div>
</template>

<script lang="ts" setup name ="Person234">



            let name ="張三"
            let age = "16"
            let tel = "123421521"
            function showTel (){
                alert(tel)
            }
     

</script>

<style scoped></style>