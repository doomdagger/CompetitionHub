/**
 * Created by JOECHOW on 2014/8/18.
 */
$(document).ready(function(){
    /*$("#uploadImage").on("change", function(){
        // Get a reference to the fileList
        var files = !!this.files ? this.files : [];

        // If no files were selected, or no FileReader support, return
        if (!files.length || !window.FileReader) return;
        // Only proceed if the selected file is an image
        if (/^image/.test( files[0].type)){
            // Create a new instance of the FileReader
            var reader = new FileReader();
            // Read the local file as a DataURL
            reader.readAsDataURL(files[0]);
            // When loaded, set image data as background of div
            reader.onloadend = function(){
                $("#uploadPreview").attr("src", this.result);
                $("#preDiv").fadeIn();
            }
        }
    });*/

    $("#btnProxy_pre").click(function(){
    	console.log("btn click");
        $("#uploadImage").click();
        
        //$("#uploadImage").change();
    });
    $("#uploadImage").change(function(){
        var obj = $("#uploadImage");
        //alert(this);
        var files = !!this.files ? this.files : [];
//        alert("in");
        // If no files were selected, or no FileReader support, return
        if (!files.length || !window.FileReader) return;
        // Only proceed if the selected file is an image
        if (/^image/.test( files[0].type)){
            // Create a new instance of the FileReader
            var reader = new FileReader();
            // Read the local file as a DataURL
            reader.readAsDataURL(files[0]);
            //console.log(reader.readAsDataURL(files[0]));
            // When loaded, set image data as background of div
            //if(this.result)
//            alert("change");
//            alert(this.result);
            reader.onloadend = function(){
                $("#uploadPreview").attr("src", this.result);
                $("#preDiv").fadeIn();
                $("#btnProxy_pre").html("更换图片");
            };
        }
    });
});
