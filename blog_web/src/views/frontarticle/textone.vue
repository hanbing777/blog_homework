<template>
  <div>

      <mavon-editor
        class="md"
        :value="content"
        :subfield="false"
        :defaultOpen="'preview'"
        :toolbarsFlag="false"
        :editable="false"
        :scrollStyle="true"
        :ishljs="true"
        style="z-index:10;"
      />
  </div>
</template>

<script>
export default {
  name: "height",
  data() {
    return {
      content:
        "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n",
    };
  },
};
</script>

<style>
</style>