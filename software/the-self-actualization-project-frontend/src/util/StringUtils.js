/*
 * Copyright 2024 - 2026 André Schepers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
export const splitSentenceUpInWords = (sentence) => {
    if (typeof sentence !== 'string') {
        return [];
    }
    return sentence.split(' ')
        .filter(word => word.length > 0)
        .map((word) => word.replace(/[.,?!]/g, ''));
}

export const getIndexOfNewWord = (first, second) => {
    let index = getFirstDiffIndex(first, second);

    if (!isNewWord(second, index)) {
        return -1;
    }

    let substring = second.substring(0, index);
    return splitSentenceUpInWords(substring).length;
}

export const getFirstDiffIndex = (first, second) => {
    if (first.length <= second) {
        return -1;
    }
    let diffIndex = 0;
    for (let i = 0; i < first.length; ++i) {
        if (first[i] !== second[i]) {
            break;
        }
        ++diffIndex;
    }
    return diffIndex;
}

export const getLastDiffIndex = (first, second) => {
    let flen = first.length;
    let slen = second.length;
    if (flen === 0 || slen === 0) {
        return -1;
    } else if (flen === 1 && slen === 1) {
        return first[0] === second[0] ? 0 : -1;
    }

    let smallestLength = flen > slen ? slen - 1 : flen - 1;
    let fIndex = flen - 1;
    let sIndex = slen - 1;

    for (let i = 0; i < smallestLength; ++i) {
        if (first[fIndex - i] !== second[sIndex - i]) {
            break;
        }
    }

    while (fIndex >= 0 && sIndex >= 0 && first[fIndex] === second[sIndex]) {
        --fIndex;
        --sIndex;
    }

}

export const isNewWord = (newString, diffIndex) => {
    if (diffIndex <= 0) {
        return false;
    }
    let isNewBySpaceInsert = newString[diffIndex] === " " &&
        (diffIndex > 0 && newString[diffIndex - 1] !== " ") &&
        (diffIndex < (newString.length - 1) && newString[diffIndex + 1] !== " ");
    let isNewByLetterInsert = newString[diffIndex] !== " " &&
        (diffIndex > 0 && newString[diffIndex - 1] === " ") &&
        (diffIndex < (newString.length - 1) && newString[diffIndex + 1] === " ");

    return isNewByLetterInsert || isNewBySpaceInsert;
}

export const isNonBlank = (...strings) => {
    let nonBlank = true;
    strings.forEach(string => {
        nonBlank = !!(nonBlank && string && string.length > 0);
    });
    return nonBlank;
}