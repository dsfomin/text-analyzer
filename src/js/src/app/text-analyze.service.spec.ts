import { HttpClient } from "@angular/common/http";
import { of } from "rxjs";
import { TextAnalyze } from "./text-analyze";
import { TextAnalyzeService } from "./text-analyze.service";

describe('TextAnalyzeService', () => {
    let httpClientSpy: jasmine.SpyObj<HttpClient>;
    let textAnalyzeService: TextAnalyzeService;
    let textAnalyzeVowels: TextAnalyze;
    let textAnalyzeConsonants: TextAnalyze;


    beforeEach(() => {
        httpClientSpy = jasmine.createSpyObj('HttpClient', ['post']);
        textAnalyzeService = new TextAnalyzeService(httpClientSpy);
        textAnalyzeVowels = new TextAnalyze("vowels", "aA ecE OiwW");
        textAnalyzeConsonants = new TextAnalyze("consonants", "aA ecE OiwW");
    });

    it('#ANALYZE TEXT ONLINE VOWELS: when input is vowels return correct map', (done: DoneFn) => {
        const expectedVowelsMap = new Map<string, number>([
            ['A', 2],
            ['E', 2],
            ['O', 1],
            ['I', 1]
        ]);

        httpClientSpy.post.and.returnValue(of(expectedVowelsMap));

        textAnalyzeService.getMapOfVowelsOrConsonantsOnline(textAnalyzeVowels).subscribe({
            next: map => {
                expect(map)
                    .withContext('expected online map')
                    .toEqual(expectedVowelsMap);
                done();
            },
            error: done.fail
        });

        expect(httpClientSpy.post.calls.count())
            .withContext('post get online map one call')
            .toBe(1);
    });


    it('#ANALYZE TEXT ONLINE CONSONANTS: when input is consonants return correct map', (done: DoneFn) => {
        const expectedConsonantsMap = new Map<string, number>([
            ['W', 2],
            ['C', 1]
        ]);

        httpClientSpy.post.and.returnValue(of(expectedConsonantsMap));

        textAnalyzeService.getMapOfVowelsOrConsonantsOnline(textAnalyzeConsonants).subscribe({
            next: map => {
                expect(map)
                    .withContext('expected online map')
                    .toEqual(expectedConsonantsMap);
                done();
            },
            error: done.fail
        });

        expect(httpClientSpy.post.calls.count())
            .withContext('post get online map one call')
            .toBe(1);
    });

    it('#ANALYZE TEXT OFFLINE CONSONANTS: when input is consonants return correct map', () => {
        const expectedConsonantsMap = new Map<string, number>([
            ['W', 2],
            ['C', 1]
        ]);

        expect(textAnalyzeService.getMapOfVowelsOrConsonantsOffline(textAnalyzeConsonants)).toEqual(expectedConsonantsMap);
    });

    it('#ANALYZE TEXT OFFLINE VOWELS: when input is vowels return correct map', () => {
        const expectedVowelsMap = new Map<string, number>([
            ['A', 2],
            ['E', 2],
            ['O', 1],
            ['I', 1]
        ]);

        expect(textAnalyzeService.getMapOfVowelsOrConsonantsOffline(textAnalyzeVowels)).toEqual(expectedVowelsMap);
    });
});