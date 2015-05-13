function delCourseware(id){
	deleteData(basePath()+"/teacher/courseware/delete/"+id);
}

function addCourseware(id){
	artDialog.open(basePath()+"/teacher/courseware/add/"+id,{
			id:"add",
			title:"添加课件",
		    padding: 0,
		    width:400,
		    height:260,
		    resize:false,
		    lock:true
		});
}
