'use client'
import React from "react";

export default function MainLayout({children}: { children: React.ReactNode }) {
    return (
        <html>
        <body>
        {children}
        </body>
        </html>
    );
}