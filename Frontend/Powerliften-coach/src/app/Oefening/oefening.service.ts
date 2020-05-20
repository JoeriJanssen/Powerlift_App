import { Injectable } from '@angular/core';
import { Oefening } from './oefening';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class OefeningService{
    constructor(private http : HttpClient){}

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    }

    //POST
    verstuurFoto(id:number,deFoto:File) : Observable<Oefening>{
        console.log("verstuurFoto()");
        const fd = new FormData();
        fd.append('image',deFoto,deFoto.name);
        return this.http.post<Oefening>("http://localhost:8082/Oefening/"+id+"/image",fd);
    }
}