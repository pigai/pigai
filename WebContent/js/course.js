function delCourse(id){
	deleteData(basePath()+"/teacher/course/delete/"+id);
}

function toCoursewares(id){
	goWithUrl(basePath()+"/teacher/courseware/"+id);
}


