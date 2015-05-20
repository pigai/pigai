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

function doGrade(id){
	artDialog.open(basePath()+"/teacher/homework/grade/"+id,{
			id:"submit",
			title:"打分",
		    padding: 0,
		    width:400,
		    height:260,
		    resize:false,
		    lock:true
		});
}


