<#ftl encoding='UTF-8'>
<#import "parts/menu.ftl" as m>
<@m.page>
    <script src="/js/jquery.js"></script>
    <script>
        function sendFile() {
            let formData = new FormData();
            let files = ($('#file'))[0]['files'];
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            $.ajax({
                type: "POST",
                url: "/files",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    alert(response)
                })
                .fail(function () {
                    alert('Error')
                });
        }
    </script>
    <div>
        <input type="file" id="file" name="file" placeholder="Имя файла..."/>
        <button onclick="sendFile()">
            Загрузить файл
        </button>
        <input type="hidden" id="file_hidden">
        <div class="filename"></div>
    </div>

</@m.page>