import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Korisnik } from 'app/model/Korisnik';
import { SearchUser } from '../model/SearchUser';


@Injectable()
export class KorisnikService {

    constructor(private _http: HttpClient) { }

    getKorisnika(): Observable<Korisnik> {
        return this._http.get<Korisnik>("/user/login");
    }

    getKorisnikeSve(): Observable<Korisnik[]> {
        return this._http.get<Korisnik[]>("/user/getAllUsers")
    }
    update(updatedUser: Korisnik) {
        return this._http.put("/user/edit", updatedUser);
      }
    
  public searchUser(sp: SearchUser): Observable<Korisnik[]> {
    return this._http.post<Korisnik[]>("/user/searchUser", sp);
  }
  public vratiKor(id:number):Observable<Korisnik>{
    return this._http.get<Korisnik>("/getAll/"+id);
}

    }
