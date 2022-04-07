import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { TextAnalyze } from './text-analyze';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzeService {
  private static non_vowels_regex = /[^aeiou]/gi;
  private static non_consonants_regex = /[^qwrtplkjhgfdszxcvbnm]/gi;

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getMapOfVowelsOrConsonantsOnline(textToAnalyze: TextAnalyze): Observable<Map<string, number>> {
    return this.http.post<Map<string, number>>(`${this.apiServerUrl}/text-analyze`, textToAnalyze);
  }

  public getMapOfVowelsOrConsonantsOffline(textToAnalyze: TextAnalyze): Map<string, number> {
    let map = new Map<string, number>();

    if (textToAnalyze.mode === 'vowels') {
      textToAnalyze.text
        .replace(TextAnalyzeService.non_vowels_regex, "")
        .toUpperCase()
        .split('')
        .forEach(s => map.get(s) ? map.set(s, map.get(s)! + 1) : map.set(s, 1));
    } else if (textToAnalyze.mode === 'consonants') {
      textToAnalyze.text
        .replace(TextAnalyzeService.non_consonants_regex, "")
        .toUpperCase()
        .split('')
        .forEach(s => map.get(s) ? map.set(s, map.get(s)! + 1) : map.set(s, 1));
    } else {
      console.log("Not found mode: " + textToAnalyze.mode);
    }

    return map;
  }
}
