const qrcodeBase64=" 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  "

// <!-- Use external code file to generate image -->
/*
        <script src="qrcodeBase64.js" type="text/javascript"></script>
        <script>
            function getBase64Img() {
                return "data:image/png;base64," + qrcodeBase64;
            }
            var base64img = getBase64Img();
            function Base64ToImage(base64img, callback) {
                var img = new Image();
                img.onload = function () {
                    callback(img);
                };
                img.src = base64img;
            }
            Base64ToImage(base64img, function (img) {
                document.getElementById('main').appendChild(img);
            });
        </script>

        <div id="main"></div>
*/
