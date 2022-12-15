export const imgUpload = (url,params)=>{
    return axios({
      method:'post',
      headers:{
        'Content-Type': 'multipart/form-data'
      },
      url: `${base}${url}`,
      data:params
    })
  }