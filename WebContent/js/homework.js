function delHomework(id){
	deleteData(basePath()+"/teacher/homework/delete/"+id);
}

function submitHomework(id){
	artDialog.open(basePath()+"/student/homework/submit/"+id,{
			id:"submit",
			title:"上传作业",
		    padding: 0,
		    width:400,
		    height:260,
		    resize:false,
		    lock:true
		});
}

