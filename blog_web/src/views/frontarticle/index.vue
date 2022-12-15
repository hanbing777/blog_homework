<template>
  <div>
    
    <mavon-editor
      v-if="zuJianWD"
      :editable="false"
      :value="zuJianWD"
      :subfield="false"
      :defaultOpen="'preview'"
      :toolbarsFlag="false"
      :scrollStyle="true"
      :ishljs="true"
      :navigation="true"
      @navigationToggle="onAddUrl"
      style="z-index: 10; width: 80%; margin-left: 10%"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      zuJianWD:
        "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n" +
        "```html <h1>123</h1> ```\n ### 12356\n```html\n<span>13246</span>\n```\n" +
        "```html <h1>123</h1> ```\n #### 12356\n```html\n<span>13246</span>\n```\n" +
        "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n" +
        "```html <h1>123</h1> ```\n ## 二二二\n```html\n<span>13246</span>\n```\n",
    };
  },
  watch: {
    zuJianWD() {
      this.onAddUrl();
    },
  },
  mounted() {
    this.onAddUrl();
  },
  methods: {
    onAddUrl() {
      this.$nextTick(function () {
        let _aList = document.querySelectorAll(".v-note-navigation-content a");
        for (let i = 0; i < _aList.length; i++) {
          let _aParent = _aList[i].parentNode;
          let _a = _aParent.firstChild;
          if (!_a.id) continue; // 把不属于导航中的a标签去掉，否则会报错
          let _text = _aParent.lastChild;
          let text = _text.textContent;
          _a.href = "#" + _a.id;
          _a.innerText = text;
          _aParent.removeChild(_text);
          console.log(_aList[i]);
        }
        console.log(_aList);
      });
    },
  },
};
</script>

<style>
</style>