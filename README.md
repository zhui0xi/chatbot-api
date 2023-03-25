# chatbot-api
ChatGPT AI 问答助手

前提
1、需要 知识星球 提供问答的页面
2、Open AI 的 API Key

大概流程
1、爬取 知识星球 的问题信息
2、把问题交给 ChatGPT 回答
3、以 ChatGPT 回答的信息自己构建 知识星球 的回答信息

初使用时
1、需要在 application.yml 填写
1)知识星球的 ID（groupId）
2)登录知识星球的 cookie
3)Open AI 的 API Key
2、把 Dockerfile 修改成自己所需的信息

