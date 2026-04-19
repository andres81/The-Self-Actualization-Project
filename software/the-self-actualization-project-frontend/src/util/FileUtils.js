/*
 * Copyright 2024 - 2025 André Schepers
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
export const readFile = (inputRef, callback) => {
    createReader(callback).readAsText(inputRef.current.files[0]);
}

export const readFileAsBase64 = (inputRef, callback) => {
    createReader(callback).readAsDataURL(inputRef.current.files[0]);
}

const createReader = (callback) => {
    const reader = new FileReader();
    reader.addEventListener("load", () => {
        callback(reader.result);
    }, false);
    return reader;
}

export const downloadJson = (filename, jsObject) => {
    if (!!!jsObject) {
        return;
    }
    const element = document.createElement("a");
    const textFile = new Blob([JSON.stringify(jsObject)], {type: 'text/plain'});
    element.href = URL.createObjectURL(textFile);
    filename = filename.replace(".json", "");
    element.download = filename + ".json";
    document.body.appendChild(element);
    element.click();
}