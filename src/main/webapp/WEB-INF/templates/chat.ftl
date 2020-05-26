<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as p>
<@p.page>
    <div class="chat" id="chat" onload="scroll()">
        <div class="chat-box" id="box">
            <#if messages??>
                <#list messages as message>
                    <#if message.owner == username>
                        <div class="bubbleWrapper">
                            <div class="inlineContainer own">
                                <div class="ownBubble own">
                                    <strong>${message.owner}</strong>
                                    <br>
                                    ${message.content}
                                </div>
                            </div>
                            <span class="own">${message.date}</span>
                        </div>
                    <#else >
                        <div class="bubbleWrapper">
                            <div class="inlineContainer">
                                <div class="otherBubble other">
                                    <strong>${message.owner}</strong>
                                    <br>
                                    ${message.content}
                                </div>
                            </div>
                            <span class="other">${message.date}</span>
                        </div>
                    </#if>
                </#list>
            <#else >
                <div class="no-msg">
                    ${language.get("chat.nomsg")}
                </div>
            </#if>
        </div>
        <div class="msg-input">
            <div style="margin: 20px">
                <div class="inp-box">
                    <input type="text" class="inp" id="message">
                </div>
                <div class="send-box">
                    <input type="hidden" name="chatId" value="${chat.id}">
                    <input onclick="sendMessage('${chat.owner.username}',
                            $('#message').val()); cleanAndScroll();" type="button" class="send">
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function scroll() {
            var block = document.getElementById("box");
            block.scrollTop = block.scrollHeight;
        }

        function cleanAndScroll() {
            document.getElementById("message").value = "";
        }

        document.body.onload = function () {
            receiveMessage('${chat.owner.username}');
            scroll();
        }
    </script>
</@p.page>