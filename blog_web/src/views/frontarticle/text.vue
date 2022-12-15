<template>
  <div>
    <el-card>
      <div class="hljs" ref="hlDiv" v-html="code"></div>
    </el-card>
  </div>
</template>

<script>
import { marked } from "marked";
import hljs from "highlight.js";
import javascript from "highlight.js/lib/languages/javascript";
  import 'highlight.js/styles/monokai-sublime.css';
// import 'mavon-editor/dist/css/index.css';
export default {
  name: "height",
  data() {
    return {
      code: "```html <h1>123</h1> ```\n # 12356\n```html\n<span>13246</span>\n```\n",
    };
  },
  mounted() {
    marked.setOptions({
      renderer: new marked.Renderer(),
      highlight: function (code) {
        return hljs.highlightAuto(code).value;
      },
      pedantic: false,
      gfm: true,
      tables: true,
      breaks: false,
      sanitize: false,
      smartLists: true,
      smartypants: false,
      xhtml: false,
    });
    this.code = marked(this.code);
    console.log(this.code)
  },
};
</script>
