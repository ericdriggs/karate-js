/*
 * The MIT License
 *
 * Copyright 2024 Karate Labs Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.karatelabs.js;

import java.util.Collection;
import java.util.Map;

public class JavaObject implements JavaMethods, ObjectLike {

    private final Object object;

    public JavaObject(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object instance, String name, Object... args) {
        return JavaUtils.invoke(instance == null ? object : instance, name, args);
    }

    @Override
    public Object get(String name) {
        return JavaUtils.get(object, name);
    }

    @Override
    public void put(String name, Object value) {
        JavaUtils.set(object, name, value);
    }

    @Override
    public void putAll(Map<String, Object> values) {
        values.forEach(this::put);
    }

    @Override
    public boolean hasKey(String name) {
        return JavaUtils.findGetter(object, name) != null;
    }

    @Override
    public Collection<String> keys() {
        return JavaUtils.propertyNames(object);
    }

    @Override
    public void remove(String name) {
        throw new RuntimeException("remove not supported on java object: " + object.getClass());
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> toMap() {
        return (Map<String, Object>) JavaUtils.toMapOrList(object);
    }

}

